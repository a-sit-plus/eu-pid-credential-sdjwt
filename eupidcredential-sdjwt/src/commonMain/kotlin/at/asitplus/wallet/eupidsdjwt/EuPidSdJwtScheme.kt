package at.asitplus.wallet.eupidsdjwt

import at.asitplus.wallet.lib.data.ConstantIndex
import at.asitplus.wallet.lib.data.ConstantIndex.CredentialRepresentation.*


/**
 * PID scheme according to [ANNEX 3.0.1 PID Rulebook, v1.9.0 from April 2025](https://github.com/eu-digital-identity-wallet/eudi-doc-architecture-and-reference-framework/blob/v1.9.0/docs/annexes/annex-3/annex-3.01-pid-rulebook.md)
 */
object EuPidSdJwtScheme : ConstantIndex.CredentialScheme {
    override val schemaUri: String = "https://wallet.a-sit.at/schemas/1.0.0/eupidsdjwt.json"
    override val sdJwtType: String = "urn:eudi:pid:1"
    override val supportedRepresentations: Collection<ConstantIndex.CredentialRepresentation> =
        listOf(SD_JWT)

    @Suppress("DEPRECATION")
    override val claimNames: Collection<String> =
        with(SdJwtAttributes) {
            listOf(
                FAMILY_NAME,
                GIVEN_NAME,
                BIRTH_DATE,

                PLACE_OF_BIRTH_COUNTRY,
                PLACE_OF_BIRTH_REGION,
                PLACE_OF_BIRTH_LOCALITY,

                NATIONALITIES,
                ADDRESS_FORMATTED,
                ADDRESS_COUNTRY,
                ADDRESS_REGION,
                ADDRESS_LOCALITY,
                ADDRESS_POSTAL_CODE,
                ADDRESS_STREET,
                ADDRESS_HOUSE_NUMBER,

                FAMILY_NAME_BIRTH,
                GIVEN_NAME_BIRTH,

                EMAIL,
                PHONE_NUMBER,
                PORTRAIT,

                EXPIRY_DATE,
                ISSUANCE_DATE,
                PERSONAL_ADMINISTRATIVE_NUMBER,
                SEX,
                ISSUING_AUTHORITY,
                ISSUING_COUNTRY,
                DOCUMENT_NUMBER,
                ISSUING_JURISDICTION,

                AGE_EQUAL_OR_OVER_12,
                AGE_EQUAL_OR_OVER_14,
                AGE_EQUAL_OR_OVER_16,
                AGE_EQUAL_OR_OVER_18,
                AGE_EQUAL_OR_OVER_21,
                AGE_IN_YEARS,
                AGE_BIRTH_YEAR,

                TRUST_ANCHOR,
            )
        }

    val requiredClaimNames: Collection<String> = with(SdJwtAttributes) {
        listOf(
            FAMILY_NAME,
            GIVEN_NAME,
            BIRTH_DATE,
            NATIONALITIES,
            EXPIRY_DATE,
            ISSUING_AUTHORITY,
            ISSUING_COUNTRY,
        )
    }

    /**
     * Per <https://github.com/eu-digital-identity-wallet/eudi-doc-architecture-and-reference-framework/pull/160>
     */
    object SdJwtAttributes {
        /** Current last name(s) or surname(s) of the user to whom the person identification data relates. */
        const val FAMILY_NAME = "family_name"

        /** Current first name(s), including middle name(s) where applicable, of the user to whom the person identification data relates. */
        const val GIVEN_NAME = "given_name"

        /** Day, month, and year on which the user to whom the person identification data relates was born. */
        const val BIRTH_DATE = "birthdate"

        /** Additional current age attestations, prefix, see [AgeEqualOrOver] */
        const val PREFIX_AGE_EQUAL_OR_OVER = "age_equal_or_over"

        /** Additional current age attestations: Attesting whether the PID User is currently over 12 years old. */
        const val AGE_EQUAL_OR_OVER_12 = "$PREFIX_AGE_EQUAL_OR_OVER.12"

        /** Additional current age attestations: Attesting whether the PID User is currently over 14 years old. */
        const val AGE_EQUAL_OR_OVER_14 = "$PREFIX_AGE_EQUAL_OR_OVER.14"

        /** Additional current age attestations: Attesting whether the PID User is currently over 16 years old. */
        const val AGE_EQUAL_OR_OVER_16 = "$PREFIX_AGE_EQUAL_OR_OVER.16"

        /** Attesting whether the User to whom the person identification data relates is currently an adult (true) or a minor (false). */
        const val AGE_EQUAL_OR_OVER_18 = "$PREFIX_AGE_EQUAL_OR_OVER.18"

        /** Additional current age attestations: Attesting whether the PID User is currently over 21 years old. */
        const val AGE_EQUAL_OR_OVER_21 = "$PREFIX_AGE_EQUAL_OR_OVER.21"

        object AgeEqualOrOver {
            /** Additional current age attestations: Attesting whether the PID User is currently over 12 years old. */
            const val EQUAL_OR_OVER_12 = "12"

            /** Additional current age attestations: Attesting whether the PID User is currently over 14 years old. */
            const val EQUAL_OR_OVER_14 = "14"

            /** Additional current age attestations: Attesting whether the PID User is currently over 16 years old. */
            const val EQUAL_OR_OVER_16 = "16"

            /** Attesting whether the User to whom the person identification data relates is currently an adult (true) or a minor (false). */
            const val EQUAL_OR_OVER_18 = "18"

            /** Additional current age attestations: Attesting whether the PID User is currently over 21 years old. */
            const val EQUAL_OR_OVER_21 = "21"
        }

        /** The current age of the User to whom the person identification data relates in years. */
        const val AGE_IN_YEARS = "age_in_years"

        /** The year when the User to whom the person identification data relates was born. */
        const val AGE_BIRTH_YEAR = "age_birth_year"

        /** Last name(s) or surname(s) of the User to whom the person identification data relates at the time of birth. */
        const val FAMILY_NAME_BIRTH = "birth_family_name"

        /** First name(s), including middle name(s), of the User to whom the person identification data relates at the time of birth. */
        const val GIVEN_NAME_BIRTH = "birth_given_name"

        /** Place of birth prefix, see [PlaceOfBirth] */
        const val PREFIX_PLACE_OF_BIRTH = "place_of_birth"

        /** The country where the PID User was born, as an Alpha-2 country code as specified in ISO 3166-1. */
        const val PLACE_OF_BIRTH_COUNTRY = "$PREFIX_PLACE_OF_BIRTH.country"

        /** The state, province, district, or local area where the PID User was born. */
        const val PLACE_OF_BIRTH_REGION = "$PREFIX_PLACE_OF_BIRTH.region"

        /** The country as an alpha-2 country code as specified in ISO 3166-1, or the state, province, district, or
         *  local area or the municipality, city, town, or village where the user to whom the person identification
         *  data relates was born. */
        const val PLACE_OF_BIRTH_LOCALITY = "$PREFIX_PLACE_OF_BIRTH.locality"

        object PlaceOfBirth {
            /** The country where the PID User was born, as an Alpha-2 country code as specified in ISO 3166-1. */
            const val COUNTRY = "country"

            /** The state, province, district, or local area where the PID User was born. */
            const val REGION = "region"

            /** The country as an alpha-2 country code as specified in ISO 3166-1, or the state, province, district, or
             *  local area or the municipality, city, town, or village where the user to whom the person identification
             *  data relates was born. */
            const val LOCALITY = "locality"
        }

        /** Address prefix, see [Address] */
        const val PREFIX_ADDRESS = "address"

        /**
         * The full address of the place where the PID User currently resides and/or can be contacted
         * (street name, house number, city etc.).
         */
        const val ADDRESS_FORMATTED = "$PREFIX_ADDRESS.formatted"

        /** The country where the PID User currently resides, as an Alpha-2 country code as specified in ISO 3166-1. */
        const val ADDRESS_COUNTRY = "$PREFIX_ADDRESS.country"

        /** The state, province, district, or local area where the PID User currently resides. */
        const val ADDRESS_REGION = "$PREFIX_ADDRESS.region"

        /** The municipality, city, town, or village where the PID User currently resides. */
        const val ADDRESS_LOCALITY = "$PREFIX_ADDRESS.locality"

        /** Postal code of the place where the PID User currently resides. */
        const val ADDRESS_POSTAL_CODE = "$PREFIX_ADDRESS.postal_code"

        /** The name of the street where the PID User currently resides. */
        const val ADDRESS_STREET = "$PREFIX_ADDRESS.street_address"

        /** The house number where the PID User currently resides, including any affix or suffix. */
        const val ADDRESS_HOUSE_NUMBER = "$PREFIX_ADDRESS.house_number"

        object Address {
            /**
             * The full address of the place where the user to whom the person identification data relates currently
             * resides or can be contacted (street name, house number, city etc.).
             */
            const val FORMATTED = "formatted"

            /** The country where the user to whom the person identification data relates currently resides, as an
             * alpha-2 country code as specified in ISO 3166-1. */
            const val COUNTRY = "country"

            /** The state, province, district, or local area where the user to whom the person identification data
             * relates currently resides. */
            const val REGION = "region"

            /** The municipality, city, town, or village where the user to whom the person identification data relates
             *  currently resides. */
            const val LOCALITY = "locality"

            /** The postal code of the place where the user to whom the person identification data relates currently
             * resides. */
            const val POSTAL_CODE = "postal_code"

            /** The name of the street where the user to whom the person identification data relates currently resides. */
            const val STREET = "street_address"

            /** The house number where the user to whom the person identification data relates currently resides,
             *  including any affix or suffix. */
            const val HOUSE_NUMBER = "house_number"
        }

        /** PID User’s gender, using a string value like `female`, `male`, or custom text values. */
        const val SEX = "sex"

        /** One or more alpha-2 country codes as specified in ISO 3166-1, representing the nationality of the user to
         *  whom the person identification data relates. */
        const val NATIONALITIES = "nationalities"

        /** Date (and if possible time) when the person identification data was issued and/or the administrative validity period of the person identification data began. */
        const val ISSUANCE_DATE = "date_of_issuance"

        /** Date (and if possible time) when the person identification data will expire. */
        const val EXPIRY_DATE = "date_of_expiry"

        /**
         * Name of the administrative authority that has issued this PID instance, or
         * the ISO 3166 Alpha-2 country code of the respective Member State if
         * there is no separate authority authorized to issue PIDs.
         */
        const val ISSUING_AUTHORITY = "issuing_authority"

        /** A number for the PID, assigned by the PID Provider. */
        const val DOCUMENT_NUMBER = "document_number"

        /** Alpha-2 country code, as defined in ISO 3166-1, of the PID Provider's country or territory. */
        const val ISSUING_COUNTRY = "issuing_country"

        /**
         * Country subdivision code of the jurisdiction that issued the PID, as
         * defined in ISO 3166-2:2020, Clause 8. The first part of the code SHALL
         * be the same as the value for [ISSUING_COUNTRY].
         */
        const val ISSUING_JURISDICTION = "issuing_jurisdiction"

        /**
         * A value assigned to the natural person that is unique among all personal administrative numbers issued by the
         * provider of person identification data. Where Member States opt to include this attribute, they shall
         * describe in their electronic identification schemes under which the person identification data is issued,
         * the policy that they apply to the values of this attribute, including, where applicable, specific conditions
         * for the processing of this value.
         */
        const val PERSONAL_ADMINISTRATIVE_NUMBER = "personal_administrative_number"

        /** Facial image of the wallet user compliant with ISO 19794-5 or ISO 39794 specifications. */
        const val PORTRAIT = "picture"

        /** Electronic mail address of the user to whom the person identification data relates, in conformance with [RFC 5322]. */
        const val EMAIL = "email"

        /** Mobile telephone number of the User to whom the person identification data relates, starting with the '+'
         * symbol as the international code prefix and the country code, followed by numbers only. */
        const val PHONE_NUMBER = "phone_number"

        /** This attribute indicates at least the URL at which a machine-readable version of the trust anchor to be used for verifying the PID can be found or looked up */
        const val TRUST_ANCHOR = "trust_anchor"

    }

}
