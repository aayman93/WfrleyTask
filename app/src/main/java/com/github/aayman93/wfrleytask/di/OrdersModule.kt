package com.github.aayman93.wfrleytask.di

import com.github.aayman93.wfrleytask.features.orders.data.data_source.OrdersService
import com.github.aayman93.wfrleytask.features.orders.data.repository.OrdersRepositoryImpl
import com.github.aayman93.wfrleytask.features.orders.domain.repository.OrdersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrdersModule {

    @Provides
    @Singleton
    fun provideOrdersService(retrofit: Retrofit): OrdersService {
        return retrofit.create(OrdersService::class.java)
    }

    @Provides
    @Singleton
    fun provideOrdersRepository(ordersService: OrdersService): OrdersRepository {
        return OrdersRepositoryImpl(ordersService)
    }
}