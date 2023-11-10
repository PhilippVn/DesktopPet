import os
dir_path = "."

files = []
index = 0

if __name__ == '__main__':
    for file_path in os.listdir(dir_path):
        # os.rename(f'{file_path}.txt', 'b.kml')
        if(file_path.endswith(".png")):
            print(f"{index}:{file_path} > {index}.png")
            os.rename(file_path,f"{index}.png")
            index += 1