text = "7,8,1,7,13,6,12,12,3,17,4,2,4,15,2,14,3,5,10,8,9,8,5,3,2,7,14,12,10,8,1,6,4,7,7,11"
b = 0
textNew = text.split(",")
print(textNew)
for i in textNew:
    num = int(i)
    if 5 <= num < 10:
        b = b + 1

print(b)

