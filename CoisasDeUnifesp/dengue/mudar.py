

import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

# Suponha que seu DataFrame seja df
# Exemplo:
df = pd.read_csv('semana.csv')

variaveis = ['media_amplitude_temp', 'media_amplitude_orvalho', 'media_amplitude_umidade',
             'max_temp_bulbo', 'media_temp_orvalho', 'soma_precipitacao']

plt.figure(figsize=(18, 12))

for i, var in enumerate(variaveis, 1):
    plt.subplot(3, 2, i)
    sns.lineplot(data=df, x='semana', y='casos', label='casos')
    sns.lineplot(data=df, x='semana', y=var, label=var)
    plt.title(f'Casos x {var}')
    plt.xlabel('Semana')
    plt.ylabel('Valor')
    plt.legend()

plt.tight_layout()
plt.show()

