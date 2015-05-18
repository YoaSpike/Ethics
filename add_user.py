import requests

r = requests.post(
    # 'http://localhost:9000'
    'https://yoaspike.herokuapp.com'
    '/accounts/add_user',
    data={
        'email': input('email: '),
        'password': input('password: ')
    }
)
print(r)
if len(r.text) < 200:
    print(r.text)
