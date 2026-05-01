package at.asitplus.wallet.csp

import at.asitplus.csp.ContentSecurityPolicySourceExpressionHashAlgorithm
import de.infix.testBalloon.framework.core.testSuite
import io.kotest.matchers.shouldBe

val ContentSecurityPolicySourceExpressionHashAlgorithmTest by testSuite {
    /**
     * just making sure that the enum names remain consistent with the specification
     */
    test("values") {
        ContentSecurityPolicySourceExpressionHashAlgorithm.sha256.name shouldBe "sha256"
        ContentSecurityPolicySourceExpressionHashAlgorithm.sha384.name shouldBe "sha384"
        ContentSecurityPolicySourceExpressionHashAlgorithm.sha512.name shouldBe "sha512"
    }
}


