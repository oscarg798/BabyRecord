package co.com.babyrecord

/**
 * Created by oscarg798 on 12/20/17.
 */
interface IBasePresenter<in T : IBaseView> {

    fun bind(view: T)

    fun unBind()
}