/*
 *
 *  * Copyright 2021 All rights are reserved by Pi App Studio
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  *
 *
 */

package com.msa.onlineshopzar.data.remote.utils




data class Resource<out T>(val status: Status, val data: T?, val error: MsaError? , val errorBody:T?){

    companion object {
        //Handles success
        fun <T> success(data: T): Resource<T> = Resource(
            status = Status.SUCCESS, data = data, error = null, errorBody = data )
        //Handles Loading
        fun <T> loading(data: T?=null): Resource<T> = Resource(
            status = Status.LOADING, data = data, error = null, errorBody = data )
        //Handles Error
        fun <T> error(data: T?=null, error: MsaError?=null, errorBody:T?=null): Resource<T> = Resource(
            status = Status.ERROR, data = data, error=error , errorBody= errorBody)
//        fun<T> idle(data:T?=null) = Resource (status = Status.NONE, data = data, error = null,errorBody = data)

    }
    enum class Status {
//        NONE,
        SUCCESS,
        ERROR,
        LOADING
    }
}

