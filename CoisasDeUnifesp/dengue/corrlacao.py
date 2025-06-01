import pandas as pd

# Carrega os dados
df = pd.read_csv('semana.csv')


# Lista de variáveis explicativas
variaveis = ['media_amplitude_temp', 'media_amplitude_orvalho', 'media_amplitude_umidade',
             'max_temp_bulbo', 'media_temp_orvalho', 'soma_precipitacao']

# Define os tipos de correlação que queremos calcular
tipos_correlacao = ['pearson', 'spearman']

# Define os lags
lags = [1, 2, 3, 4,5,6,7,8,9,10]

for lag in lags:
    print(f"\n--- com LAG de {lag} semana(s) ---")
    df_lag = df.copy()
    
    # Aplica lag às variáveis explicativas
    for var in variaveis:
        df_lag[f'{var}_lag{lag}'] = df_lag[var].shift(lag)
    
    # Dataframe só com colunas defasadas + casos
    colunas_lag = [f'{var}_lag{lag}' for var in variaveis] + ['casos']
    df_corr = df_lag[colunas_lag].dropna()
    
    for metodo in tipos_correlacao:
        corr = df_corr.corr(method=metodo, numeric_only=True)['casos'].drop('casos').sort_values(key=abs, ascending=False)
        print(f"\nMétodo: {metodo}")
        print(corr)
