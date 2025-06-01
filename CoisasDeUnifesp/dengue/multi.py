import pandas as pd
from multiprocessing import Pool, cpu_count
import os
from tqdm import tqdm

def processar_arquivo(ano):
    arquivo = f'sinan/DENGBR{ano}.csv'
    print(f'[INFO] Processando {arquivo} (PID: {os.getpid()})')

    if not os.path.exists(arquivo):
        print(f'[AVISO] Arquivo {arquivo} não encontrado. Pulando...')
        return

    partes_filtradas = []

    try:
        # 先获取文件行数，方便进度条
        total_linhas = sum(1 for _ in open(arquivo, 'r', encoding='utf-8', errors='ignore')) - 1  # 减去表头
        chunksize = 100000
        total_chunks = (total_linhas // chunksize) + 1

        with tqdm(total=total_chunks, desc=f'Lendo {ano}', leave=False) as pbar:
            for chunk in pd.read_csv(arquivo, chunksize=chunksize, low_memory=False):
                try:
                    chunk_filtrado = chunk[
                        (chunk['ID_MN_RESI'] == 355030) 
                    ]
                    partes_filtradas.append(chunk_filtrado)
                except KeyError as e:
                    print(f'[ERRO] Coluna não encontrada em {arquivo}: {e}')
                    return
                pbar.update(1)

        if partes_filtradas:
            df_sp_autoctone = pd.concat(partes_filtradas)
            df_sp_autoctone.to_csv(arquivo, index=False, encoding='utf-8')
            print(f'[OK] {arquivo} salvo com sucesso.')
        else:
            print(f'[INFO] Nenhum dado correspondente encontrado em {arquivo}.')

    except Exception as e:
        print(f'[ERRO] Falha ao processar {arquivo}: {e}')


if __name__ == "__main__":
    anos = range(14, 24)  # de 2019 a 2023
    with Pool(processes=cpu_count() // 2 or 1) as pool:
        # 用 imap_unordered 可以边处理边显示进度
        for _ in tqdm(pool.imap_unordered(processar_arquivo, anos), total=len(anos), desc='Arquivos'):
            pass

    print("[INFO] Todos os arquivos foram processados.")

    # selecionei dados de sP 14-23