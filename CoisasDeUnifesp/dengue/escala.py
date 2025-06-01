


import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.preprocessing import MinMaxScaler
# Suponha que seu DataFrame seja df
# Exemplo:
df = pd.read_csv('semana.csv')



scaler = MinMaxScaler()

# Seleciona as colunas para normalizar
cols = ['casos', 'media_amplitude_temp', 'media_amplitude_orvalho',
        'media_amplitude_umidade', 'max_temp_bulbo', 'media_temp_orvalho', 'soma_precipitacao']

df_norm = df.copy()
df_norm[cols] = scaler.fit_transform(df[cols])

plt.figure(figsize=(15,10))

for var in cols:
    plt.plot(df_norm['semana'], df_norm[var], label=var)

plt.xlabel('Semana')
plt.ylabel('Valor Normalizado')
plt.title('Variáveis normalizadas por Semana')
plt.legend()
plt.show()
