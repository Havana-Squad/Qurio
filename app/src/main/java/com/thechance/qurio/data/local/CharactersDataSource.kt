package com.thechance.qurio.data.local

import android.content.Context
import com.thechance.qurio.R
import com.thechance.qurio.domain.entity.Character

object CharactersDataSource {

    private var charactersCache: MutableList<Character>? = null

    fun getAllCharacters(context: Context): List<Character> {
        if (charactersCache == null) {
            charactersCache = createCharacters(context).toMutableList()
        }
        return charactersCache!!.toList()
    }

    fun updateCharacterLockState(id: Int, isUnlocked: Boolean) {
        charactersCache?.let { list ->
            val index = list.indexOfFirst { it.id == id }
            if (index != -1) {
                list[index] = list[index].copy(isUnlocked = isUnlocked)
            }
        }
    }

    fun updateCharacterUsedState(id: Int, isUsed: Boolean) {
        charactersCache?.let { list ->
            if (isUsed) {
                list.forEachIndexed { index, character ->
                    list[index] = character.copy(isCharacterUsed = false)
                }
            }

            val index = list.indexOfFirst { it.id == id }
            if (index != -1) {
                list[index] = list[index].copy(isCharacterUsed = isUsed)
            }
        }
    }


    fun getCharacterById(id: Int): Character? {
        return charactersCache?.firstOrNull { it.id == id }
    }
    fun resetCharacters(context: Context) {
        charactersCache = createCharacters(context).toMutableList()
    }

    private fun createCharacters(context: Context): List<Character> = listOf(
        Character(
            id = 1,
            name = context.getString(R.string.rika),
            description = context.getString(R.string.nature_s_little_explorer_rika_talks_to_mushrooms_and_swears_squirrels_give_her_battle_advice_always_ready_for_an_adventure),
            age = context.getString(R.string._12_sunblooms),
            imageLockedRes = R.drawable.rika,
            imageUnlockedRes = R.drawable.rika_selected,
            isUnlocked = true,
            isCharacterUsed = false,
            price=0,

        ),
        Character(
            id = 2,
            name = context.getString(R.string.kaiyo),
            description = context.getString(R.string.a_calm_storm_in_human_form_kaiyo_trains_with_ancient_scrolls_by_day_and_drinks_spicy_tea_by_night_sword_sharp_heart_sharper),
            age = context.getString(R.string._14_storms),
            imageLockedRes = R.drawable.kaiyo_closed,
            imageUnlockedRes = R.drawable.kaiyo,
            isUnlocked = false,
            isCharacterUsed = false,
            price=300,
        ),
        Character(
            id = 3,
            name = context.getString(R.string.mimi),
            description = context.getString(R.string.tiny_but_terrifying_mimi_is_always_grumpy_but_don_t_let_that_scare_you_unless_you_like_pranks_involving_firecrackers),
            age = context.getString(R.string._10_volcano_puffs),
            imageLockedRes = R.drawable.mimi_closed,
            imageUnlockedRes = R.drawable.mimi,
            isUnlocked = false,
            isCharacterUsed = false,
            price=700,
        ),
        Character(
            id = 4,
            name = context.getString(R.string.yoru),
            description = context.getString(R.string.quiet_mysterious_and_probably_watching_you_right_now_yoru_shows_up_when_you_least_expect_it),
            age = context.getString(R.string._13_shadows),
            imageLockedRes = R.drawable.yoru_closed,
            imageUnlockedRes = R.drawable.yoru,
            isUnlocked = false,
            isCharacterUsed = false,
            price=1000,
        ),
        Character(
            id = 5,
            name = context.getString(R.string.kuro),
            description = context.getString(R.string.cool_jacket_cooler_moves_kuro_never_backs_down_from_a_challenge),
            age = context.getString(R.string._15_thunder_beats),
            imageLockedRes = R.drawable.kuro_closed,
            imageUnlockedRes = R.drawable.kuro,
            isUnlocked = false,
            isCharacterUsed = false,
            price=3000,
        ),
        Character(
            id = 6,
            name = context.getString(R.string.miko),
            description = context.getString(R.string.energetic_cheerful_and_faster_than_a_leaf_in_the_wind_miko_can_turn_any_trivia_into_a_giggle_fest),
            age = context.getString(R.string._11_leaf_turns),
            imageLockedRes = R.drawable.miko_closed,
            imageUnlockedRes = R.drawable.miko,
            isUnlocked = false,
            isCharacterUsed = false,
            price=7000,
        ),
        Character(
            id = 7,
            name = context.getString(R.string.aori),
            description = context.getString(R.string.the_sword_chooses_the_wielder_and_it_chose_aori_calm_focused),
            age = context.getString(R.string._13_blade_echoes),
            imageLockedRes = R.drawable.aori_closed,
            imageUnlockedRes = R.drawable.aori,
            isUnlocked = false,
            isCharacterUsed = false,
            price=12000,
        ),
        Character(
            id = 8,
            name = context.getString(R.string.nara),
            description = context.getString(R.string.part_magic_part_sass_nara_sparkles_even_when_she_s_mad),
            age = context.getString(R.string._12_crystal_songs),
            imageLockedRes = R.drawable.nara_closed,
            imageUnlockedRes = R.drawable.nara,
            isUnlocked = false,
            isCharacterUsed = false,
            price=30000,
        ),
        Character(
            id = 9,
            name = context.getString(R.string.renji),
            description = context.getString(R.string.small_but_mighty_renji_dreams_of_glory_carries_a_shield_too_big_for_him),
            age = context.getString(R.string._11_hero_coins),
            imageLockedRes = R.drawable.renji_closed,
            imageUnlockedRes = R.drawable.renji,
            isUnlocked = false,
            isCharacterUsed = false,
            price=50000,
        ),
    )
}