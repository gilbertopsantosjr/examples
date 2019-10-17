

import pandas as pd
import io
import requests
import os

def readAppleStoreApi(filename, path_to_save):
  data = pd.read_csv(filename)
  data.sort_values(["rating_count_tot"], axis=0, ascending=False, inplace=True)
  data = data.loc[ (data['prime_genre'] == "Music") | (data['prime_genre'] == "Book") | (data['prime_genre'] == "News")]
  data = data[['id','track_name', 'rating_count_tot','size_bytes', 'price', 'prime_genre']]
  data = data.nlargest(10, ['rating_count_tot'])
  data.columns = [['id','track_name', 'n_citacoes','size_bytes', 'price', 'prime_genre']]
  return data.to_csv (os.path.join(path_to_save,r'apple_store.csv'), index = None, header=True)


result_cvs = readAppleStoreApi('AppleStore.csv', '/Volumes/Mac/workspace/python/')