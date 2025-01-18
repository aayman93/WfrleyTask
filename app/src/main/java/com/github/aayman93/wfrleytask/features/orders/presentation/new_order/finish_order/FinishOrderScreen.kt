package com.github.aayman93.wfrleytask.features.orders.presentation.new_order.finish_order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.aayman93.wfrleytask.R
import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderDetail
import com.github.aayman93.wfrleytask.features.orders.domain.models.Product
import com.github.aayman93.wfrleytask.features.orders.presentation.components.OrderProduct
import com.github.aayman93.wfrleytask.features.orders.presentation.components.OrdersTextField
import com.github.aayman93.wfrleytask.features.orders.presentation.components.OrdersTopBar
import com.github.aayman93.wfrleytask.features.orders.presentation.new_order.NewOrderViewModel
import com.github.aayman93.wfrleytask.features.orders.presentation.new_order.finish_order.components.OrderFinishedDialog
import com.github.aayman93.wfrleytask.ui.theme.Neutral100
import com.github.aayman93.wfrleytask.ui.theme.Neutral200
import com.github.aayman93.wfrleytask.ui.theme.Neutral500
import com.github.aayman93.wfrleytask.ui.theme.Neutral600
import com.github.aayman93.wfrleytask.ui.theme.Primary700
import com.github.aayman93.wfrleytask.ui.theme.Success600
import com.github.aayman93.wfrleytask.ui.theme.Text16SemiBold
import com.github.aayman93.wfrleytask.ui.theme.Text20Regular
import com.github.aayman93.wfrleytask.utils.ObserveAsEvents
import com.github.aayman93.wfrleytask.utils.showToast

@Composable
fun FinishOrderScreen(
    sharedViewModel: NewOrderViewModel,
    onBackClick: () -> Unit
) {
    val viewModel = hiltViewModel<FinishOrderViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val sharedState by sharedViewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.prepareOrderItems(sharedState.selectedProducts)
    }

    ObserveAsEvents(viewModel.eventFlow) { event ->
        when (event) {
            is FinishOrderEvent.OnError -> {
                showToast(context = context, message = event.error)
            }

            FinishOrderEvent.OnOrderSuccess -> {
                sharedViewModel.onOrderFinished()
                onBackClick()
            }
        }
    }

    FinishOrderScreenContent(
        state = state,
        onPaidAmountChanged = viewModel::onPaidAmountChanged,
        onCustomerNameChanged = viewModel::onCustomerNameChanged,
        onCustomerPhoneChanged = viewModel::onCustomerPhoneChanged,
        onConfirmOrderClick = viewModel::onConfirmOrder,
        onDialogDismissed = viewModel::onDialogDismissed,
        onBackClick = onBackClick
    )
}

@Composable
fun FinishOrderScreenContent(
    state: FinishOrderState,
    onPaidAmountChanged: (String) -> Unit,
    onCustomerNameChanged: (String) -> Unit,
    onCustomerPhoneChanged: (String) -> Unit,
    onConfirmOrderClick: () -> Unit,
    onDialogDismissed: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            OrdersTopBar(
                title = stringResource(R.string.finish_order),
                onBackClick = onBackClick,
                hasHomeAction = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    .background(Primary700)
                    .statusBarsPadding()
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing
    ) { innerPadding ->
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp

        if (state.isDialogVisible) {
            OrderFinishedDialog(
                onDismiss = onDialogDismissed
            )
        }

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
        ) {
            if (!state.isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .verticalScroll(state = rememberScrollState())
                ) {
                    Spacer(Modifier.height(24.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(6.dp))
                                .background(Neutral100)
                                .padding(16.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = stringResource(R.string.cash),
                                style = Text20Regular,
                                color = Neutral600,
                                textAlign = TextAlign.Start
                            )
                        }

                        OrdersTextField(
                            modifier = Modifier.weight(1f),
                            value = state.paidAmount,
                            onValueChange = onPaidAmountChanged,
                            placeholder = stringResource(R.string.paid_amount),
                            keyboardType = KeyboardType.Number
                        )
                    }
                    Spacer(Modifier.height(24.dp))
                    Text(
                        text = stringResource(R.string.order_info),
                        style = Text16SemiBold,
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))
                    OrdersTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.customerName,
                        onValueChange = onCustomerNameChanged,
                        placeholder = stringResource(R.string.customer_name)
                    )
                    Spacer(Modifier.height(16.dp))
                    OrdersTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.customerPhone,
                        onValueChange = onCustomerPhoneChanged,
                        placeholder = stringResource(R.string.phone),
                        keyboardType = KeyboardType.Phone
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = screenHeight),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(bottom = 16.dp)
                    ) {
                        items(
                            items = state.orderItems,
                            key = { item -> item.id }
                        ) { item ->
                            OrderProduct(
                                orderDetail = item,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Primary700
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(91.dp)
                    .background(Color.White)
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(59.dp),
                    enabled = state.isFinishOrderEnabled && !state.isSaving,
                    onClick = onConfirmOrderClick,
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Success600,
                        disabledContainerColor = if (state.isFinishOrderEnabled && state.isSaving) Success600 else Neutral200,
                        contentColor = Color.White,
                        disabledContentColor = if (state.isFinishOrderEnabled && state.isSaving) Color.White else Neutral500
                    )
                ) {
                    if (state.isFinishOrderEnabled && state.isSaving) {
                        CircularProgressIndicator(
                            color = Color.White
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.finish_order),
                            style = Text16SemiBold
                        )
                    }
                }
            }
        }
    }
}

@Preview(locale = "ar", showSystemUi = true)
@Composable
private fun FinishOrderScreenPreview() {
    FinishOrderScreenContent(
        state = FinishOrderState(
            paidAmount = "50",
            customerName = "احمد",
            customerPhone = "01234567890",
            orderItems = listOf(
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
            isSaving = false
        ),
        onPaidAmountChanged = {},
        onCustomerNameChanged = {},
        onCustomerPhoneChanged = {},
        onConfirmOrderClick = {},
        onDialogDismissed = {},
        onBackClick = {}
    )
}