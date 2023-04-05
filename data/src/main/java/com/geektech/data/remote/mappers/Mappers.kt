package com.geektech.data.remote.mappers

import com.geektech.data.remote.model.*
import com.geektech.domain.model.*

fun MangaResponseDto.toModel() = MangaResponse(
    count = count,
    next = next,
    previous = previous,
    results = results.map {
        it.toModel()
    } as ArrayList<MangaResult>
)

fun MangaResultDto.toModel() = MangaResult(
    id = id,
    en_name = en_name,
    ru_name = ru_name,
    dir = dir,
    image = image,
    description = description,
    chapters_quantity = chapters_quantity,
    issue_year = issue_year,
    created_at = created_at,
    updated_at = updated_at,
    type = type,
    likes = likes,
    rating = rating,
    genre = genre
)

fun UserDto.toModel() = User(
    status = status,
    username = username,
    nickname = nickname,
    message = message
)

fun User.toDto() = UserDto(
    status = status,
    username = username,
    nickname = nickname,
    message = message
)

fun MangaCommentsDto.toModel() = MangaComments(
    id = id,
    user = user.toModel(),
    text = text,
)

fun MangaAppUserDto.toModel() = MangaAppUser(
    id = id,
    username = username,
    nickname = nickname,
    image = image,
    image_file = image_file
)

fun MangaComments.toDto() = MangaCommentsDto(
    id = id,
    user = user.toDto(),
    text = text,
)

fun MangaAppUser.toDto() = MangaAppUserDto(
    id = id,
    username = username,
    nickname = nickname,
    image = image,
    image_file = image_file
)

fun LoginResponseDto.toModel() = LoginResponse(
    status = status,
    user = user,
    access = access,
    refresh = refresh
)

fun LoginResponse.toDto() = LoginResponseDto(
    status = status,
    user = user,
    access = access,
    refresh = refresh
)

fun LoginRequest.toDto() = LoginRequestDto(
    username = username,
    password = password
)

fun LoginRequestDto.toModel() = LoginRequest(
    username = username,
    password = password
)

fun RefreshToken.toDto() = RefreshTokenDto(
    access = access
)

fun RefreshTokenDto.toModel() = RefreshToken(
    access = access
)

fun AddCommentResponseDto.toModel() = AddCommentResponse(
    text = text
)

fun AddCommentResponse.toDto() = AddCommentResponseDto(
    text = text
)

fun GenresDto.toModel() = Genres(
    id = id,
    title = title
)

fun Genres.toDto() = GenresDto(
    id = id,
    title = title
)

