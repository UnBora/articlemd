# Article management

Articles Management បង្កើតឡើងដើម្បី Practice ក្នុងការចាប់ផ្ដើមរៀននិងស្វែងយល់ពី Spring data jpa។ ក្នង Project មួយនេះយើងក៏អាចដឹងពីការ Upload and Download File ផងដែរ។

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
| `article     `| `string` | ចំណងជើង, បរិយាពីប្រធានបទ, ឈ្មោះអ្នកនិពន្ធ របស់  Article (ប្រភេទជា  RequestParam) Example=    *{ "**articleTitle**": "រឿងផ្កាស្រពោន", "**description**": "រឿងផ្កាស្រពោន គឺជាប្រលោមលោកដ៏ប្រជាប្រិយមួយនៅប្រទេសកម្ពុជា ហើយរឿងនេះក៏ត្រូវបានថតជាខ្សែភាពយន្តផងដែរ។ ប្រលោមលោកដ៏ប្រជាប្រិយនេះ គឺជាស្នាដៃដ៏ឆ្នើមរបស់កវីនិពន្ធ រដ្ឋា ចាន់ថន ដែលបាននិពន្ធឡើងនៅព.ស.២៤៩០ ត្រូវនឹងគ.ស.១៩៣៨", "**authorName**": "រដ្ឋា ចាន់ថន" }*    |
| `file`        | `MultipartFile`   | រូបភាបដែលត្រូវ Upload        |

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
| `article     `| `string` | ចំណងជើង, បរិយាពីប្រធានបទ, ឈ្មោះអ្នកនិពន្ធ របស់  Article (ប្រភេទជា RequestParam) Example=    *{ "**articleTitle**": "រឿងកុលាបប៉ៃលិន", "**description**": "រឿងកុលាបប៉ៃលិន ជាស្នាដៃនិពន្ធរបស់លោកញ៉ុក ថែម ដែលបាននិពន្ធកាលពីព.ស.២៥០៤ ត្រូវនឹង គ.ស.១៩៣៦ឬឆ្នាំ១៩៤៣ ", "**authorName**": "ញ៉ុក ថែម" }*    |
| `file`        | `MultipartFile`   | រូបភាបដែលត្រូវ Upload (RequestParam) |


#### Delete Article

```http:localhost:8080
  Delete /api/v1/deleteArticle/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int`    | **Required**. ត្រូវការ id ដើម្បីទៅ Delete​ Article (Change statuse to 0​ |



#### Disable Article

```http:localhost:8080
  Post /api/v1/disable/{id}
```

| Parameter | Type     | Description                                                          |
| :-------- | :------- | :--------------------------------                                    |
| `id`      | `int`    | **Required**. ត្រូវការ id ដើម្បីទៅ Disable Article (Change statuse to 1  |


#### Enable Article

```http:localhost:8080
  Post /api/v1/enable/{id}
```

| Parameter | Type     | Description                                                        |
| :-------- | :------- | :--------------------------------                                  |
| `id`      | `int`    | **Required**. ត្រូវការ id ដើម្បីទៅ Enable Article (Change statuse to 2 |

...

## 🚀 About Me
សួស្ដី! ខ្ញុំ បូរ៉ា The one who create this project for you!

