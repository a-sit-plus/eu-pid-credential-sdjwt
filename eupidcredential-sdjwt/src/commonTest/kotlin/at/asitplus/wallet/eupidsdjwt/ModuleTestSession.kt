package at.asitplus.wallet.eupidsdjwt

import de.infix.testBalloon.framework.core.TestSession

class ModuleTestSession : TestSession() {
    init {
        Initializer.initWithVCK()
    }
}