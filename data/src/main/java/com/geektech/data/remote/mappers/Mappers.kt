package com.geektech.data.remote.mappers

import com.geektech.data.remote.model.MangaResponseDto
import com.geektech.data.remote.model.MangaResultDto
import com.geektech.data.remote.model.UserDto
import com.geektech.data.remote.model.UserRegisterBodyDto
import com.geektech.domain.model.MangaResponse
import com.geektech.domain.model.MangaResult
import com.geektech.domain.model.User
import com.geektech.domain.model.UserRegisterBody

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

fun UserRegisterBodyDto.toUserRegisterBody() = UserRegisterBody(
    username = username,
    nickname = nickname,
    image_file = image_file,
    password = password
)

fun UserRegisterBody.toUserRegisterBodyDto() = UserRegisterBodyDto(
    username = username,
    nickname = nickname,
    image_file = image_file,
    password = password
)

