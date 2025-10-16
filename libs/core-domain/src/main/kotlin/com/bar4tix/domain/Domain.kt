
package com.bar4tix.domain

import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

@JvmInline value class TenantId(val value: UUID)
@JvmInline value class LojaId(val value: UUID)
@JvmInline value class ProdutoId(val value: UUID)
enum class PriceSource { CUPOM, MANUAL, PANFLETO, OCR }
data class Preco(val amount: BigDecimal, val currency: String = "BRL")

sealed interface DomainEvent { val id: UUID; val occurredAt: Instant; val tenantId: TenantId }
data class PriceObserved(
  override val id: UUID = UUID.randomUUID(),
  override val occurredAt: Instant = Instant.now(),
  override val tenantId: TenantId,
  val lojaId: LojaId,
  val produtoId: ProdutoId,
  val valor: Preco,
  val source: PriceSource
): DomainEvent
