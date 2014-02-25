function printdata(obj)
{
    var text = obj.value;
    alert(text);
}

function foo()
{
    alert('код ошибки 0x12A02045B. обнаружено слишком много тыканий по картинке');
}

function restrictinput(input)
{
    var numArg = Number(input.value);
    if(numArg<=-5 || numArg>=5)
    {
        alert('ошибка:вы ввели данные неверного диапазона!');
        return false;
    }
    if(isNaN(numArg))
    {
        alert('ошибка:вы ввели нечисловые данные!');
        return false;
    }
    if(input.value==="")
    {
        alert('введена пустая строка!');
        return false;
    }
    return true;
}
