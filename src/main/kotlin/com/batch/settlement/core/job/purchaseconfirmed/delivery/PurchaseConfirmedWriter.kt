package com.batch.settlement.core.job.purchaseconfirmed.delivery

import com.batch.settlement.domain.entity.order.OrderItem
import com.batch.settlement.infrastructure.database.repository.OrderItemRepository
import org.jetbrains.annotations.NotNull
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Component
@Transactional
class PurchaseConfirmedWriter(
    private val orderItemRepository: OrderItemRepository
): ItemWriter<OrderItem> {

    override fun write(@NotNull chunk: Chunk<out OrderItem>) {
        for (item in chunk.items) {
            val newItem = item.copy(id = item.id, purchaseConfirmedAt = ZonedDateTime.now())

            orderItemRepository.save(newItem)
        }
    }
}