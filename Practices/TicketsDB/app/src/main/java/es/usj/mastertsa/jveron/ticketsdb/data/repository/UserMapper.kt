package es.usj.mastertsa.jveron.ticketsdb.data.repository

import es.usj.mastertsa.jveron.ticketsdb.data.repository.room.UserDbModel
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User
import java.security.spec.KeySpec
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import kotlin.random.Random

object UserMapper {

    fun mapUserFromDbToDomain(userDbModel: UserDbModel): User {
        return User(
            email = userDbModel.user_email,
            password = userDbModel.password,
            name = userDbModel.name
        )
    }
    
    fun mapUserFromDomainToDb (user: User): UserDbModel {
        return UserDbModel(
            user_email = user.email,
            password = user.password,
            name = user.name
        )
    }
    
    fun saltAndHash(password: String): String{
        val salt = ByteArray(16)
        Random.nextBytes(salt)
        val spec: KeySpec = PBEKeySpec(password.toCharArray(), salt, 65536, 128)
        val f: SecretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
        val hash: ByteArray = f.generateSecret(spec).getEncoded()
        val enc: Base64.Encoder = Base64.getEncoder()
        return enc.encodeToString(hash)
    }
}