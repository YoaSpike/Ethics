import requests

q = input('Online? [Y/N] ')
BASE = (
    'http://localhost:9000' if q.lower() in {'n', 'nay', 'no'}
    else 'https://yoaspike.herokuapp.com'
)

r = requests.post(
    BASE + '/accounts/add_user',
    data={
        'email': input('email: '),
        'password': input('password: ')
    }
)
print(r)
if len(r.text) < 200:
    print(r.text)
