package at.asitplus.wallet.eupidsdjwt

import de.infix.testBalloon.framework.core.testSuite
import io.kotest.matchers.shouldBe
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
val SelectiveDisclosureConstraintsTest by testSuite {
    /**
     * just making sure that the enum names remain consistent with the specification
     */
    test("values") {
        SelectiveDisclosureConstraints.always.name shouldBe "always"
        SelectiveDisclosureConstraints.allowed.name shouldBe "allowed"
        SelectiveDisclosureConstraints.never.name shouldBe "never"
    }
}



