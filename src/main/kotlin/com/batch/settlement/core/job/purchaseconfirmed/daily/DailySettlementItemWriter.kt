package com.batch.settlement.core.job.purchaseconfirmed.daily

import com.batch.settlement.domain.entity.settlement.SettlementDaily
import com.batch.settlement.infrastructure.database.repository.SettlementDailyRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter

class DailySettlementItemWriter(
    private val settlementDailyRepository: SettlementDailyRepository
) : ItemWriter<SettlementDaily> {

    override fun write(chunk: Chunk<out SettlementDaily>) {
        for (item in chunk.items) {
            settlementDailyRepository.save(item)
        }
    }
}