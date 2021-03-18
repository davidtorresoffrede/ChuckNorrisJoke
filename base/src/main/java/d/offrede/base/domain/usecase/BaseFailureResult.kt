package d.offrede.base.domain.usecase

object Unexpected : Exception("Ocorreu um erro inesperado")
object Network : Exception("Houve uma falha de conexão, verifique sua internet e tente novamente")