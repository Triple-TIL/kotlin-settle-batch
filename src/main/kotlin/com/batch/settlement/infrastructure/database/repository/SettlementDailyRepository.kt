package com.batch.settlement.infrastructure.database.repository

import com.batch.settlement.domain.entity.settlement.SettlementDaily
import org.springframework.data.jpa.repository.JpaRepository

interface SettlementDailyRepository: JpaRepository<SettlementDaily, Long> {
}