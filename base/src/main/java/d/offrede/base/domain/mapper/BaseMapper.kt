package d.offrede.base.domain.mapper



abstract class BaseMapper<T, R> {
    abstract fun transform(source: T): R
}