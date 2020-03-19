package practice.toppop.data.model

class NetworkResponse(val status: NetworkStatus, val message: String?) {

    enum class NetworkStatus {
        ERROR, SUCCESS, LOADING
    }

    companion object {

        fun error(message: String?): NetworkResponse {
            return NetworkResponse(NetworkStatus.ERROR,  message)
        }

        fun success(): NetworkResponse {
            return NetworkResponse(NetworkStatus.SUCCESS, null)
        }

        fun loading(): NetworkResponse{
            return NetworkResponse(NetworkStatus.LOADING, null)
        }

    }

}