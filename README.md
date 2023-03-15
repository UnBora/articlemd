# Article management

Articles Management បង្កើតឡើងដើម្បី Practice ក្នុងការចាប់ផ្ដើមរៀននិងស្វែងយល់ពី Spring data jpa។ ក្នង Project មួយនេះយើងក៏អាចដឹងពីការ Upload and Download File ផងដែរ។
![Capture](https://user-images.githubusercontent.com/74696117/225243156-be2fc6a2-d9e8-4657-a835-dcc2d3196264.PNG)

## Project Features

- Create Article
- Get All Articles
- Get Article By ID
- Update Article By ID
- Delete Article By ID
- Disable Article By ID
- Enable Article By ID


## API Articles Management


#### Post Article

```http:localhost:8080
  Post /api/v1/add
```

| Parameter     | Type     | Description                |
| :--------     | :------- | :------------------------                           |
| `article     `| `string` |  ![Capture](https://user-images.githubusercontent.com/74696117/225247351-01854eb7-3887-4606-9833-0500b8282cb5.PNG)   |
| `file`        | `MultipartFile` | រូបភាបដែលត្រូវ Upload        |

#### Get all Articles

```http:localhost:8080
  Get /api/v1/articles
```

| Parameter | Type     | Description                                                                                      |
| :-------- | :------- | :-------------------------                                                                       |
| `pageNo`  | `int`    | **Default**. 1  (page ទី1)                                                                        |
| `pageSize`| `int`    | **Default**  5  (1 page មាន article 5)                                                           |
| `status`  | `int`    | **Default**. 2 (0= article ត្រូវបានលុបហើយ, 1 = article ត្រូវបាន  disable, 2= article កំពុង  active )|

#### Get Article

```http:localhost:8080
  GET /api/v1/article/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int`    | **Required**. ត្រូវការ id ដើម្បីទៅចាប់យក​ Article យកមក​ |

#### Update Article

```http:localhost:8080
  Post /api/v1/updateArticle/{id}
```

| Parameter     | Type     | Description                | 
| :--------     | :------- | :------------------------- |
| `id`          | `int`    | id របស់ user (ប្រភេទជា PathVariable)      |
| `article     `| `string` | ![Capture](https://user-images.githubusercontent.com/74696117/225247351-01854eb7-3887-4606-9833-0500b8282cb5.PNG)    |
| `file`        | `MultipartFile`   | រូបភាបដែលត្រូវ Upload (RequestParam) |


#### Delete Article

```http:localhost:8080
  Delete /api/v1/deleteArticle/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int`    | **Required**. ត្រូវការ id ដើម្បីទៅ Delete​ Article (Change statuse to 0) |



#### Disable Article

```http:localhost:8080
  Post /api/v1/disable/{id}
```

| Parameter | Type     | Description                                                          |
| :-------- | :------- | :--------------------------------                                    |
| `id`      | `int`    | **Required**. ត្រូវការ id ដើម្បីទៅ Disable Article (Change statuse to 1)  |


#### Enable Article

```http:localhost:8080
  Post /api/v1/enable/{id}
```

| Parameter | Type     | Description                                                        |
| :-------- | :------- | :--------------------------------                                  |
| `id`      | `int`    | **Required**. ត្រូវការ id ដើម្បីទៅ Enable Article (Change statuse to 2) |

...

## 🚀 About Me
សួស្ដី! ខ្ញុំ បូរ៉ា ចូលចិត្តអានសៀវភៅកូនក្មេង
![Timmy2](https://user-images.githubusercontent.com/74696117/225252531-d72f8712-2b91-4a49-8b72-613d446d7912.png)
