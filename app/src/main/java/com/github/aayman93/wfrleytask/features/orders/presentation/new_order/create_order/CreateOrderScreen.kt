package com.github.aayman93.wfrleytask.features.orders.presentation.new_order.create_order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.aayman93.wfrleytask.R
import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderProduct
import com.github.aayman93.wfrleytask.features.orders.domain.models.Product
import com.github.aayman93.wfrleytask.features.orders.presentation.components.OrdersTopBar
import com.github.aayman93.wfrleytask.features.orders.presentation.new_order.NewOrderState
import com.github.aayman93.wfrleytask.features.orders.presentation.new_order.NewOrderViewModel
import com.github.aayman93.wfrleytask.features.orders.presentation.new_order.create_order.components.CartSummary
import com.github.aayman93.wfrleytask.features.orders.presentation.new_order.create_order.components.ProductItem
import com.github.aayman93.wfrleytask.features.orders.presentation.new_order.create_order.components.SearchBar
import com.github.aayman93.wfrleytask.ui.theme.Primary700
import com.github.aayman93.wfrleytask.utils.ObserveAsEvents
import com.github.aayman93.wfrleytask.utils.showToast

@Composable
fun CreateOrderScreen(
    sharedViewModel: NewOrderViewModel,
    onConfirmOrder: () -> Unit,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {

    val viewModel = hiltViewModel<CreateOrderViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val sharedState by sharedViewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    ObserveAsEvents(viewModel.eventFlow) { event ->
        when (event) {
            is CreateOrderEvent.OnError -> {
                showToast(context = context, message = event.error)
            }
        }
    }

    CreateOrderScreenContent(
        state = state,
        sharedState = sharedState,
        onSearchQueryChanged = viewModel::onQueryChanged,
        onAddToOrder = sharedViewModel::onAddToOrder,
        onRemoveFromOrder = sharedViewModel::onRemoveFromOrder,
        onConfirmOrder = onConfirmOrder,
        onBackClick = onBackClick,
        onHomeClick = onHomeClick
    )
}

@Composable
private fun CreateOrderScreenContent(
    state: CreateOrderState,
    sharedState: NewOrderState,
    onSearchQueryChanged: (String) -> Unit,
    onAddToOrder: (Product) -> Unit,
    onRemoveFromOrder: (Product) -> Unit,
    onConfirmOrder: () -> Unit,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    Scaffold(
        topBar = {
            OrdersTopBar(
                title = stringResource(R.string.create_new_order),
                onBackClick = onBackClick,
                onHomeClick = onHomeClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    .background(Primary700)
                    .statusBarsPadding()
            )
        },
        contentWindowInsets = WindowInsets.safeContent
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(Modifier.height(24.dp))
                SearchBar(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.query,
                    onValueChange = onSearchQueryChanged,
                    placeholder = stringResource(R.string.search_for_product)
                )
                Spacer(Modifier.height(24.dp))
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(
                        bottom = if (sharedState.isOrderEmpty) {
                            16.dp
                        } else {
                            96.dp
                        }
                    )
                ) {
                    itemsIndexed(
                        items = sharedState.selectedProducts,
                        key = { index, _ -> index }
                    ) { _, item: OrderProduct ->
                        ProductItem(
                            modifier = Modifier
                                .weight(1f)
                                .height(171.dp),
                            product = item.product,
                            quantityInOrder = item.quantity,
                            onAddToOrder = {
                                onAddToOrder(item.product)
                            },
                            onRemoveFromOrder = {
                                onRemoveFromOrder(item.product)
                            }
                        )
                    }
                    items(
                        items = state.searchProducts,
                        key = { item -> item.id }
                    ) { item: Product ->
                        ProductItem(
                            modifier = Modifier
                                .weight(1f)
                                .height(171.dp),
                            product = item,
                            onAddToOrder = {
                                onAddToOrder(item)
                            }
                        )
                    }
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Primary700
                )
            }

            if (!sharedState.isOrderEmpty) {
                CartSummary(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 24.dp)
                        .zIndex(1f),
                    quantity = sharedState.totalQuantity,
                    price = sharedState.totalPrice,
                    onClick = {
                        onConfirmOrder()
                    }
                )
            }
        }
    }
}

@Preview(locale = "ar", showSystemUi = true)
@Composable
private fun CreateOrderScreenPreview() {
    CreateOrderScreenContent(
        state = CreateOrderState(),
        sharedState = NewOrderState(),
        onSearchQueryChanged = {},
        onAddToOrder = {},
        onRemoveFromOrder = {},
        onConfirmOrder = {},
        onBackClick = {},
        onHomeClick = {}
    )
}