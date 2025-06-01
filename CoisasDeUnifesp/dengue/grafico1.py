import pandas as pd
import matplotlib.pyplot as plt

# Carrega os dados
df = pd.read_csv('semana.csv')

# Lista de variáveis explicativas (exceto 'casos')
variaveis = ['media_amplitude_temp', 'media_amplitude_orvalho', 'media_amplitude_umidade',
             'max_temp_bulbo', 'media_temp_orvalho', 'soma_precipitacao']

# Gráficos separados: cada um compara uma variável com casos
for var in variaveis:
    fig, ax1 = plt.subplots(figsize=(10, 4))
    
    # Eixo da esquerda: casos
    ax1.plot(df['semana'], df['casos'], color='blue', label='Casos')
    ax1.set_ylabel('Casos', color='blue')
    ax1.set_xlabel('Semana')
    ax1.tick_params(axis='y', labelcolor='blue')
    
    # Eixo da direita: variável explicativa
    ax2 = ax1.twinx()
    ax2.plot(df['semana'], df[var], color='orange', label=var)
    ax2.set_ylabel(var, color='orange')
    ax2.tick_params(axis='y', labelcolor='orange')

    plt.title(f'Casos x {var}')
    plt.tight_layout()
    plt.show()
