package com.batch.settlement.domain.collection

import com.batch.settlement.domain.entity.order.OrderItem
import com.batch.settlement.domain.entity.settlement.SettlementDaily
import java.time.LocalDate

class PositiveDailySettlementCollection(
    private val item: OrderItem
) {

    fun getSettlementDaily(): SettlementDaily {
        val orderItemSnapshot = item.orderItemSnapshot
        val count = item.orderCount ?: 1
        val countToDecimal = count.toBigDecimal()
        val seller = orderItemSnapshot.seller

        // 세금 계산
        val taxCalculator = TaxCalculator(orderItemSnapshot)
        val taxAmount = taxCalculator.getTaxAmount().multiply(countToDecimal)

        // 정산금액 데이터 만들기
        val pgCalculator = PgSalesAmountCalculator(orderItemSnapshot)
        val pgSalesAmount = pgCalculator.getPgSaleAmount().multiply(countToDecimal)

        val commissionAmountCalculator = CommissionAmountCalculator(orderItemSnapshot)
        val commissionAmount = commissionAmountCalculator.getCommissionAmount().multiply(countToDecimal)

        return SettlementDaily(
            settlementDate = LocalDate.now(),
            orderNo = item.order.id,
            orderCount = count,
            orderItemNo = item.orderItemSnapshot.id,
            sellerNo = orderItemSnapshot.seller.id,
            sellerBusinessNumber = seller.businessNo,
            sellerName = seller.sellerName,
            sellType = seller.sellType,
            taxType = orderItemSnapshot.taxType,
            taxAmount = taxAmount,
            commissionAmount = commissionAmount,
            pgSalesAmount = pgSalesAmount,
            couponDiscountAmount = orderItemSnapshot.promotionAmount,
            mileageUsageAmount = orderItemSnapshot.mileageUsageAmount,
            shippingFeeAmount = orderItemSnapshot.defaultDeliveryAmount
        )
    }

}