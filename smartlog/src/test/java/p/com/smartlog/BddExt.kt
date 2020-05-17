@file:Suppress("TestFunctionName")

package p.com.smartlog

inline fun Given(description: String, block: (()->Unit)){
    block.invoke()
}
inline fun When(description: String, block: (()->Unit)){
    block.invoke()
}

inline fun Then(description: String, block: (()->Unit)){
    block.invoke()
}

inline fun Scenario(description: String, block: (()->Unit)){
    block.invoke()
}

