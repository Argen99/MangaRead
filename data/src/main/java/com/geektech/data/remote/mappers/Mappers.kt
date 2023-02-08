package com.geektech.data.remote.mappers

import com.geektech.data.remote.model.*
import com.geektech.domain.model.*

fun MangaResponseDto.toMangaResponse() = MangaResponse(
    count = count,
    next = next,
    previous = previous,
    results = results.map {
        it.toMangaResult()
    } as ArrayList<MangaResult>
)

fun MangaResultDto.toMangaResult() = MangaResult(
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

fun UserDto.toUser() = User(
    status = status,
    username = username,
    nickname = nickname,
    message = message
)

fun User.toUserDto() = UserDto(
    status = status,
    username = username,
    nickname = nickname,
    message = message
)

fun MangaCommentsDto.toMangaComments() = MangaComments(
    id = id,
    user = user.toMangaAppUser(),
    text = text,
)

fun MangaAppUserDto.toMangaAppUser() = MangaAppUser(
    id = id,
    username = username,
    nickname = nickname,
    image = image,
    image_file = image_file
)

fun MangaComments.toMangaCommentsDto() = MangaCommentsDto(
    id = id,
    user = user.toMangaAppUserDto(),
    text = text,
)

fun MangaAppUser.toMangaAppUserDto() = MangaAppUserDto(
    id = id,
    username = username,
    nickname = nickname,
    image = image,
    image_file = image_file
)

fun LoginResponseDto.toLoginResponse() = LoginResponse(
    status = status,
    user = user,
    access = access,
    refresh = refresh
)

fun LoginResponse.toLoginResponseDto() = LoginResponseDto(
    status = status,
    user = user,
    access = access,
    refresh = refresh
)

fun LoginRequest.toLoginRequestDto() = LoginRequestDto(
    username = username,
    password = password
)

fun LoginRequestDto.toLoginRequest() = LoginRequest(
    username = username,
    password = password
)

fun RefreshToken.toRefreshTokenDto() = RefreshTokenDto(
    access = access
)

fun RefreshTokenDto.toRefreshToken() = RefreshToken(
    access = access
)

fun AddCommentResponseDto.toAddCommentResponse() = AddCommentResponse(
    text = text
)

fun AddCommentResponse.toAddCommentResponseDto() = AddCommentResponseDto(
    text = text
)

fun GenresDto.toGenres() = Genres(
    id = id,
    title = title
)

fun Genres.toGenresDto() = GenresDto(
    id = id,
    title = title
)

