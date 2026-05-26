import subprocess
import pickle
import hashlib
import yaml
import random
import tempfile
import os

PASSWORD = "hardcoded_password"

def bad_hash(data):
    return hashlib.md5(data.encode()).hexdigest()

def unsafe_yaml(data):
    return yaml.load(data, Loader=yaml.Loader)

def unsafe_pickle(data):
    return pickle.loads(data)

def command_injection(user_input):
    subprocess.call(f"ls {user_input}", shell=True)

def dangerous_eval(expr):
    return eval(expr)

def weak_random():
    return random.random()

def insecure_temp():
    return tempfile.mktemp()

def dangerous_os(cmd):
    os.system(cmd)

assert PASSWORD == "admin"
