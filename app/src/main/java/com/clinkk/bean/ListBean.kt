package com.clinkk.bean

data class ListBean(
    val clinics: List<Clinic>
) {
    data class Clinic(
        val address: Address,
        val distance: Distance,
        val id: String,
        val images: List<String>,
        val name: String,
        val status: String,
        val timings: List<Timing>,
        val translations: Translations
    ) {
        data class Address(
            val city: String,
            val country: String,
            val lat: Double,
            val line: String,
            val lon: Double,
            val state: String,
            val zipcode: String
        )

        data class Distance(
            val from: From,
            val unit: String,
            val value: String
        ) {
            data class From(
                val lat: Double,
                val lon: Double
            )
        }

        data class Timing(
            val day: String,
            val shifts: List<Shift>,
            val status: String
        ) {
            data class Shift(
                val closing_time: String,
                val name: String,
                val opening_time: String
            )
        }

        data class Translations(
            val bn: Bn,
            val en: En,
            val gu: Gu,
            val hi: Hi,
            val kn: Kn,
            val ml: Ml,
            val mr: Mr,
            val or: Or,
            val pa: Pa,
            val ta: Ta,
            val te: Te
        ) {
            data class Bn(
                val address: Address,
                val name: String
            ) {
                data class Address(
                    val city: String,
                    val country: String,
                    val line: String,
                    val state: String,
                    val zipcode: String
                )
            }

            data class En(
                val address: Address,
                val name: String
            ) {
                data class Address(
                    val city: String,
                    val country: String,
                    val line: String,
                    val state: String,
                    val zipcode: String
                )
            }

            data class Gu(
                val address: Address,
                val name: String
            ) {
                data class Address(
                    val city: String,
                    val country: String,
                    val line: String,
                    val state: String,
                    val zipcode: String
                )
            }

            data class Hi(
                val address: Address,
                val name: String
            ) {
                data class Address(
                    val city: String,
                    val country: String,
                    val line: String,
                    val state: String,
                    val zipcode: String
                )
            }

            data class Kn(
                val address: Address,
                val name: String
            ) {
                data class Address(
                    val city: String,
                    val country: String,
                    val line: String,
                    val state: String,
                    val zipcode: String
                )
            }

            data class Ml(
                val address: Address,
                val name: String
            ) {
                data class Address(
                    val city: String,
                    val country: String,
                    val line: String,
                    val state: String,
                    val zipcode: String
                )
            }

            data class Mr(
                val address: Address,
                val name: String
            ) {
                data class Address(
                    val city: String,
                    val country: String,
                    val line: String,
                    val state: String,
                    val zipcode: String
                )
            }

            data class Or(
                val address: Address,
                val name: String
            ) {
                data class Address(
                    val city: String,
                    val country: String,
                    val line: String,
                    val state: String,
                    val zipcode: String
                )
            }

            data class Pa(
                val address: Address,
                val name: String
            ) {
                data class Address(
                    val city: String,
                    val country: String,
                    val line: String,
                    val state: String,
                    val zipcode: String
                )
            }

            data class Ta(
                val address: Address,
                val name: String
            ) {
                data class Address(
                    val city: String,
                    val country: String,
                    val line: String,
                    val state: String,
                    val zipcode: String
                )
            }

            data class Te(
                val address: Address,
                val name: String
            ) {
                data class Address(
                    val city: String,
                    val country: String,
                    val line: String,
                    val state: String,
                    val zipcode: String
                )
            }
        }
    }
}