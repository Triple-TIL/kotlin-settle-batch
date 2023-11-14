package com.batch.settlement.core.job.purchaseconfirmed.daily

import com.batch.settlement.domain.collection.PositiveDailySettlementCollection
import com.batch.settlement.domain.entity.order.OrderItem
import com.batch.settlement.domain.entity.settlement.SettlementDaily
import org.springframework.batch.item.ItemProcessor

class DailySettlementItemProcessor : ItemProcessor<OrderItem, SettlementDaily> {

    override fun process(item: OrderItem): SettlementDaily {
        val positiveDailySettlementCollection = PositiveDailySettlementCollection(item)

        return positiveDailySettlementCollection.getSettlementDaily()
    }
}