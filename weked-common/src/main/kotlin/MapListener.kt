class SingleMapListener<P1, R1>(
    private val event: SingleParameterCallback<R1>,
    private val map: (R1) -> P1
) : SingleParameterCallback<P1> {

    override fun addListener(listener: (P1) -> Unit) = event.addListener { listener(map(it)) }

    override fun removeListener(listener: (P1) -> Unit) = event.removeListener { listener(map(it)) }

    override fun hasListener(listener: (P1) -> Unit) = event.hasListener { listener(map(it)) }

}

class DualMapListener<P1, P2, R1, R2>(
    private val event: DualParameterCallback<R1, R2>,
    private val map1: (R1) -> P1,
    private val map2: (R2) -> P2,
) : DualParameterCallback<P1, P2> {

    private fun conversion(listener: (P1, P2) -> Unit) = { r1: R1, r2: R2 -> listener(map1(r1), map2(r2)) }

    override fun addListener(listener: (P1, P2) -> Unit) = event.addListener(conversion(listener))
    override fun removeListener(listener: (P1, P2) -> Unit) = event.removeListener(conversion(listener))
    override fun hasListener(listener: (P1, P2) -> Unit) = event.hasListener(conversion(listener))

}
