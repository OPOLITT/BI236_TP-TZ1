#!/bin/bash

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

# Выводим список директорий
find "$input_dir" -mindepth 1 -type d | while read dir; do
    dirname=$(basename -- "$dir")
    echo "Реализовано получение списка директорий, находящихся во входной директории - $dirname"
done


find "$input_dir" -maxdepth 1 -type f | while IFS= read -r file; do
    filename=$(basename -- "$file")

    if [[ "$filename" != ".DS_Store" ]]; then
        echo "Реализовано получение списка файлов, находящихся непосредственно во входной директории (не во вложенных в нее директориях) - $filename"
    fi
done

# Начало цирка + тут обрабатываем файлы которые можно читать  и не читаем '.DS_Store'
find "$input_dir"  -type f ! -name '.DS_Store' | while IFS= read -r file; do
    filename=$(basename -- "$file")

    echo "Реализовано получение списка всех файлов, вложенных во входную директорию - $filename"

    # Проверяем на символические ссылки
    if [[ -L "$file" ]]; then
        echo "Пропускаем символическую ссылку - $file"
        continue
    fi
    
    # Проверяем на права чтения
    if [[ ! -r "$file" ]]; then
        echo "Нет прав на чтение - $file"
        continue
    fi
    
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
