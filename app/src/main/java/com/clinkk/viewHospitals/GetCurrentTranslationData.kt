package com.clinkk.viewHospitals

import com.clinkk.sharedPref.KeyValue
import com.clinkk.sharedPref.SharedPref
import com.clinkk.viewHospitals.bean.ClinicBean

object GetCurrentTranslationData {

    fun getData(translation: ClinicBean.Clinic.Translations): TransalationData {
        when (SharedPref.get().get(KeyValue.current_translation, "en")) {

            "bn" -> {
                return TransalationData(
                    TransalationData.Address(
                        city = translation.bn.address.city,
                        country = translation.bn.address.country,
                        line = translation.bn.address.line,
                        state = translation.bn.address.state,
                        zipcode = translation.bn.address.zipcode
                    ), translation.bn.name
                )
            }
            "gu" -> {
                return TransalationData(
                    TransalationData.Address(
                        city = translation.gu.address.city,
                        country = translation.gu.address.country,
                        line = translation.gu.address.line,
                        state = translation.gu.address.state,
                        zipcode = translation.gu.address.zipcode
                    ), translation.gu.name
                )
            }
            "hi" -> {
                return TransalationData(
                    TransalationData.Address(
                        city = translation.hi.address.city,
                        country = translation.hi.address.country,
                        line = translation.hi.address.line,
                        state = translation.hi.address.state,
                        zipcode = translation.hi.address.zipcode
                    ), translation.hi.name
                )
            }
            "kn" -> {
                return TransalationData(
                    TransalationData.Address(
                        city = translation.kn.address.city,
                        country = translation.kn.address.country,
                        line = translation.kn.address.line,
                        state = translation.kn.address.state,
                        zipcode = translation.kn.address.zipcode
                    ), translation.kn.name
                )
            }
            "ml" -> {
                return TransalationData(
                    TransalationData.Address(
                        city = translation.ml.address.city,
                        country = translation.ml.address.country,
                        line = translation.ml.address.line,
                        state = translation.ml.address.state,
                        zipcode = translation.ml.address.zipcode
                    ), translation.ml.name
                )
            }
            "or" -> {
                return TransalationData(
                    TransalationData.Address(
                        city = translation.or.address.city,
                        country = translation.or.address.country,
                        line = translation.or.address.line,
                        state = translation.or.address.state,
                        zipcode = translation.or.address.zipcode
                    ), translation.or.name
                )
            }
            "pa" -> {
                return TransalationData(
                    TransalationData.Address(
                        city = translation.pa.address.city,
                        country = translation.pa.address.country,
                        line = translation.pa.address.line,
                        state = translation.pa.address.state,
                        zipcode = translation.pa.address.zipcode
                    ), translation.pa.name
                )
            }
            "ta" -> {
                return TransalationData(
                    TransalationData.Address(
                        city = translation.ta.address.city,
                        country = translation.ta.address.country,
                        line = translation.ta.address.line,
                        state = translation.ta.address.state,
                        zipcode = translation.ta.address.zipcode
                    ), translation.ta.name
                )
            }
            "te" -> {
                return TransalationData(
                    TransalationData.Address(
                        city = translation.te.address.city,
                        country = translation.te.address.country,
                        line = translation.te.address.line,
                        state = translation.te.address.state,
                        zipcode = translation.te.address.zipcode
                    ), translation.te.name
                )
            }
            "mr" -> {
                return TransalationData(
                    TransalationData.Address(
                        city = translation.mr.address.city,
                        country = translation.mr.address.country,
                        line = translation.mr.address.line,
                        state = translation.mr.address.state,
                        zipcode = translation.mr.address.zipcode
                    ), translation.mr.name
                )
            }
            else -> return TransalationData(
                TransalationData.Address(
                    city = translation.en.address.city,
                    country = translation.en.address.country,
                    line = translation.en.address.line,
                    state = translation.en.address.state,
                    zipcode = translation.en.address.zipcode
                ), translation.en.name
            )
        }
    }

    data class TransalationData(
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