package com.github.aayman93.wfrleytask.features.orders.presentation.order_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.aayman93.wfrleytask.R
import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderDetail
import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderDetails
import com.github.aayman93.wfrleytask.features.orders.domain.models.Product
import com.github.aayman93.wfrleytask.features.orders.domain.models.ShippingAddress
import com.github.aayman93.wfrleytask.features.orders.presentation.components.OrderProduct
import com.github.aayman93.wfrleytask.features.orders.presentation.components.OrdersTopBar
import com.github.aayman93.wfrleytask.features.orders.presentation.order_details.components.OrderInfo
import com.github.aayman93.wfrleytask.features.orders.presentation.order_details.components.OrderSummary
import com.github.aayman93.wfrleytask.ui.theme.Neutral50
import com.github.aayman93.wfrleytask.ui.theme.Neutral600
import com.github.aayman93.wfrleytask.ui.theme.Primary700
import com.github.aayman93.wfrleytask.ui.theme.Text16SemiBold
import com.github.aayman93.wfrleytask.ui.theme.Text24SemiBold
import com.github.aayman93.wfrleytask.utils.ObserveAsEvents
import com.github.aayman93.wfrleytask.utils.showToast
import com.github.aayman93.wfrleytask.utils.toDate
import com.github.aayman93.wfrleytask.utils.toTime

@Composable
fun OrderDetailsScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    val viewModel = hiltViewModel<OrderDetailsViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    ObserveAsEvents(viewModel.eventFlow) { event ->
        when (event) {
            is OrderDetailsEvent.OnError -> {
                showToast(context = context, message = event.error)
            }
        }
    }

    OrderDetailsScreenContent(
        state = state,
        onBackClick = onBackClick,
        onHomeClick = onHomeClick
    )
}

@Composable
private fun OrderDetailsScreenContent(
    state: OrderDetailsState,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Scaffold(
        topBar = {
            OrdersTopBar(
                title = if (state.orderDetails == null) {
                    ""
                } else {
                    stringResource(
                        R.string.invoice_number_with_value,
                        state.orderDetails.orderNumber.ifEmpty { "1" }
                    )
                },
                onBackClick = onBackClick,
                onHomeClick = onHomeClick,
                contentColor = Neutral600,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    .background(Color.White)
                    .statusBarsPadding()
            )
        }
    ) { innerPadding ->
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(Neutral50),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Primary700)
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(Neutral50)
                    .verticalScroll(state = rememberScrollState())
            ) {
                state.orderDetails?.let { orderDetails: OrderDetails ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Spacer(Modifier.height(24.dp))
                        if (orderDetails.orderDate.isNotEmpty()) {
                            Text(
                                text = orderDetails.orderDate.toDate(),
                                style = Text24SemiBold,
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = "(${orderDetails.orderDate.toTime()})",
                                style = Text24SemiBold,
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.height(24.dp))
                        }
                        OrderInfo(
                            modifier = Modifier.fillMaxWidth(),
                            customerName = orderDetails.customerName,
                            phone = orderDetails.shippingAddress.telephone,
                            address = "${orderDetails.shippingAddress.cityName} ${orderDetails.shippingAddress.street}".trim()
                        )
                        Spacer(Modifier.height(24.dp))
                        Text(
                            text = stringResource(R.string.order_items),
                            style = Text16SemiBold,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(Modifier.height(16.dp))
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth()
                                .heightIn(max = screenHeight),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(
                                items = orderDetails.items,
                                key = { item -> item.id }
                            ) { item ->
                                OrderProduct(
                                    orderDetail = item,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                        Spacer(Modifier.height(24.dp))
                    }
                    OrderSummary(
                        modifier = Modifier.fillMaxWidth()
                            .padding(bottom = 24.dp),
                        paymentMethod = stringResource(R.string.cash),
                        totalDiscount = orderDetails.discountAmount,
                        totalPrice = orderDetails.grandTotal
                    )
                }
            }
        }
    }
}

@Preview(locale = "ar", showSystemUi = true)
@Composable
private fun OrderDetailsScreenPreview() {
    OrderDetailsScreenContent(
        state = OrderDetailsState(
            orderDetails = OrderDetails(
                id = 1,
                orderNumber = "50",
                orderDate = "2025-01-14T12:38:06.3058988",
                customerName = "احمد",
                discountAmount = 50.0,
                grandTotal = 100.0,
                items = listOf(
                    OrderDetail(
                        id = 1,
                        quantity = 3.0,
                        rowPriceAfterDiscount = 50.0,
                        rowTotal = 62.5,
                        product = Product(
                            name = "توست ريتش بيك",
                            price = 35.0
                        )
                    )
                ),
                shippingAddress = ShippingAddress(
                    cityName = "المنصورة",
                    street = "شارع الجيش",
                    telephone = "01234567890"
                )
            )
        ),
        onBackClick = {},
        onHomeClick = {}
    )
}