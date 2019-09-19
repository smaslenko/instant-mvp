package com.stonemas.instantmvp

import android.util.Log
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*


object ModelsUtil {

    fun generateId(): String {
        return UUID.randomUUID().toString()
    }

}

//@Suppress("UNCHECKED_CAST")
//fun <M : BaseModel> BaseActivity<M>.getMClass(): Class<M> {
//    return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<M>
//}

@Suppress("UNCHECKED_CAST")
fun <M : MvpModel, V : MvpView, P : MvpPresenter<M, V>> MvpActivity<M, V, P>.getMClass(): Class<M> {
    return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<M>
}

@Suppress("UNCHECKED_CAST")
fun <M : MvpModel, V : MvpView, P : MvpPresenter<M, V>> MvpFragment<M, V, P>.getMClass(): Class<M> {
    return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<M>
}


fun <M : MvpModel, V : MvpView, P : MvpPresenter<M, V>> initPresenter(parentObj: Any, model: M): P {
    val tClazz = getClassFromGeneric<P>(parentObj, 2)
    val constructor = tClazz.getConstructor()
    val presenter = constructor.newInstance()
    presenter.attachModel(model)
    return presenter
}

@Suppress("UNCHECKED_CAST")
@Throws(Exception::class)
fun <T> getClassFromGeneric(
        parentObj: Any,
        oridnalParamterizedTypeIndex: Int): Class<T> {
    val typeArray = getParameterizedTypeListAsArray<Any>(parentObj)
    return typeArray[oridnalParamterizedTypeIndex] as Class<T>
}

fun <T> getParameterizedTypeListAsArray(parentObj: Any): Array<Type> {
    try {
        return (parentObj.javaClass
                .genericSuperclass as ParameterizedType)
                .actualTypeArguments
    } catch (e: ClassCastException) {
        Log.e("ModelsUtil", "Most likely, somewhere in your inhetirance chain,"
                + "there is a class that uses a raw type and not the generic param."
                + "See: http://stackoverflow.com/questions/23074446/java-lang-classcastexception-java-lang-class-cannot-be-cast-to-java-lang-reflec"
                + " for more info", e)
        throw e
    }

}
