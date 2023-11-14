package com.batch.settlement.domain.entity.order

import com.batch.settlement.domain.entity.Product
import com.batch.settlement.domain.entity.Seller
import com.batch.settlement.domain.enums.TaxType
import com.batch.settlement.domain.enums.TaxTypeConverter
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.ZonedDateTime

@Entity
data class OrderItemSnapshot(
    @Id @Column(name = "order_item_snapshot_no") var id: Long,

    var createdAt: ZonedDateTime? = ZonedDateTime.now(),
    var updatedAt: ZonedDateTime? = ZonedDateTime.now(),
    var deletedAt: ZonedDateTime? = null,

    var sellPrice: BigDecimal? = BigDecimal.ZERO,
    var supplyPrice: BigDecimal? = BigDecimal.ZERO,
    var promotionAmount: BigDecimal? = BigDecimal.ZERO,
    var defaultDeliveryAmount: BigDecimal? = BigDecimal.valueOf(3000),
    val mileageUsageAmount: BigDecimal? = BigDecimal.ZERO,

    var itemCategory: Int? = 0,
    var taxRate: Int? = 3,

    @Convert(converter = TaxTypeConverter::class)
    var taxType: TaxType? = TaxType.TAX,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no", referencedColumnName="product_no", insertable = false, updatable = false)
    var product: Product,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="seller_no", referencedColumnName = "seller_no", insertable = false, updatable = false)
    var seller: Seller,
)