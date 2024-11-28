package br.com.facol.uservault.convertes;


public interface Mapper<T, U> {
    U map(T t);
}
