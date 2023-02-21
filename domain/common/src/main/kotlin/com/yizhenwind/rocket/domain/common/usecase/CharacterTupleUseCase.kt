package com.yizhenwind.rocket.domain.common.usecase

import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.common.ext.pickFirstOrDefault
import com.yizhenwind.rocket.core.common.usecase.IDataFlowUseCase
import com.yizhenwind.rocket.core.mediator.character.ICharacterService
import com.yizhenwind.rocket.core.model.CharacterTuple
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/20
 */
class CharacterTupleUseCase @Inject constructor(
    private val characterService: ICharacterService
) : IDataFlowUseCase<TupleContext> {

    override fun execute(data: TupleContext): Flow<TupleContext> =
        data.run {
            if (clientTuple.id == Constant.DEFAULT_ID) {
                flowOf(
                    data.copy(
                        characterTupleList = emptyList(),
                        characterTuple = CharacterTuple()
                    )
                )
            } else if (accountTuple.id != Constant.DEFAULT_ID) {
                characterService.observeCharacterTupleListByAccountId(accountTuple.id)
                    .map { characterTupleList ->
                        data.copy(
                            characterTupleList = characterTupleList,
                            characterTuple = characterTupleList.pickFirstOrDefault(CharacterTuple()) { it.id == data.characterTuple.id }
                        )
                    }
            } else {
                characterService.observeCharacterTupleListByClientId(clientTuple.id)
                    .map { characterTupleList ->
                        val characterTuple =
                            characterTupleList.pickFirstOrDefault(CharacterTuple()) { it.id == data.characterTuple.id }
                        data.copy(
                            accountTuple = characterTuple.accountTuple,
                            characterTupleList = characterTupleList,
                            characterTuple = characterTuple
                        )
                    }
            }
        }
}