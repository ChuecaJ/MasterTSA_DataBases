package es.usj.mastertsa.jveron.ticketsdb.data.repository

import es.usj.mastertsa.jveron.ticketsdb.data.repository.room.UserDbModel
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User

object UserMapper {
    
    // TODO(This needs UserApiModel)
    // fun mapUserFromApiToDomain(userApiModel: UserApiModel) : User {
    //     return User(
    //         id = userApiModel.id,
    //         email = userApiModel.price,
    //         password = userApiModel.password,
    //         name = userApiModel.name
    //     )
    // }
    
    fun mapSightFromDbToDomain(userDbModel: UserDbModel): User {
        return User(
            id = userDbModel.user_id,
            email = userDbModel.email,
            password = userDbModel.password,
            name = userDbModel.name
        )
    }
    
    fun mapSightFromDomainToDb (user: User): UserDbModel {
        return UserDbModel(
            user_id = user.id,
            email = user.email,
            password = user.password,
            name = user.name
        )
    }
}