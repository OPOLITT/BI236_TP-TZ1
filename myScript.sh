# При запуске скрипта прочитали директории
input_dir="$1"
output_dir="$2"


# Проверка
if [[ "$#" -ne 2 ]]; then
    echo "Задано не 2 аргумента"
    exit 1
fi

# Проверка
if [[ ! -d "$input_dir" ]]; then
    echo "Входная директория не существует."
    exit 1
fi

# Если нет выходной директории, то создаем 
mkdir -p "$output_dir"


# Начало цирка
find "$input_dir" -type f | while read file; do
    filename=$(basename -- "$file")
    echo "$filename"
    
    # Если нет файла в директории output, то сразу же прокидываем файл туда, иначе..
    if [[ ! -e "$output_dir/$filename" ]]; then
        cp "$file" "$output_dir"
    else
        # Счетчик чтобы одинаковым файлом менять название 
        count=1
        base="${filename%.*}" 
        extension="${filename##*.}"
     
        # Создали новый файл (добавили цифру на конце)
        new_filename="${base}_$count.$extension"

        # Если файлов однотипных много, то тут каунтер считает 
        while [[ -e "$output_dir/$new_filename" ]]; do
            count=$((count+1))
            new_filename="${base}_$count.$extension"
        done

        # Копируем в директорию
        cp "$file" "$output_dir/$new_filename"
    fi
done
