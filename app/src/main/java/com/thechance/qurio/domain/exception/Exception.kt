package com.thechance.qurio.domain.exception

open class QurioException(message: String = "Qurio Exception"): Exception()

class NoInternetException(message: String = "No Internet"): QurioException(message)
class UnknownException(message: String = "Unknown"): QurioException(message)