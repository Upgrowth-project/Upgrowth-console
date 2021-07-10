package ru.honfate.upgrowth.core.model.blocks

import ru.honfate.upgrowth.core.model.Core
import ru.honfate.upgrowth.core.model.Variable
import ru.honfate.upgrowth.core.model.exception.TypeMismatchException
import ru.honfate.upgrowth.core.model.types.*
import ru.honfate.upgrowth.core.model.types.basic.EmptyValue


class DeclarationBlock(private val newVariable: Variable, // предусловие: newVariable было создано без инициализации
                       private val initBlock: Block?): Block {

    private val _returnType: Type

    init {
        if (newVariable.type is EmptyValue || initBlock == null ||
            newVariable.type == initBlock.returnType)
                _returnType = newVariable.type
        else throw TypeMismatchException(newVariable.type, initBlock.returnType)
    }

    override val returnType: Type
        get() = _returnType

    override val additionalContext: Context
        get() = contextOf(newVariable)

    override fun run(core: Core, arguments: Context): TypedValue {
        if (initBlock != null) {
            newVariable.set(initBlock.run(core, arguments))
        }
        return newVariable.typedValue
    }
}