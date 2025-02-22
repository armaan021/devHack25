##version 1.0

special_characters = "@#!$%/*&"

def details_form():
    first_name = input("Enter your first name : ")
    last_name = input("Enter your last name : ")
    preferred_name = input("Enter your preffered name : ")
    date_of_birth = input("Enter your date of birth in mm-dd-yyyy : ")
    hometown = input("Enter your hometown")

    return first_name, last_name, preferred_name, date_of_birth, hometown

def initial_password():
    is_upper = False
    is_lower = False
    is_length = False
    is_number = False
    is_special = False

    password = input("Enter a password : ")

    if len(password) < 16:
        is_length = True

    for i in password:
        if i.isupper():
            is_upper = True
        elif i.islower():
            is_lower = True
        elif i.isnumeric():
            is_number = True
        elif i in special_characters:
            is_special = True

    if is_length and is_lower and is_number and is_special and is_upper:
        return password
    else:
        return "Invalid Password!"
    
def confirm_password(init_password):
    confirm_pass = input("Confirm your password : ")
    if confirm_pass == init_password:
        return True
    else:
        return False
    
f = open("Questions.txt", "r")
questions = [line.rstrip() for line in f.readlines()]
f.close()

def gameplay():
    print("WELCOME TO THE GAME")
    print("-------------------")

    first_name, last_name, preferred_name, date_of_birth, hometown = details_form

    length_ques = input(questions[0] + " : ")

def length_ques_func(length):
    return

password = initial_password()
print(password)
print(confirm_password(password))
