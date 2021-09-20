/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jk.auth;

/**
 *
 * @author CTA-PROGRAMADOR
 */
public class JwtConfig {

    public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\n"
            + "MIIEpAIBAAKCAQEApUy28DCqyiq9wOCKd0U76hBNx40s18vh30OoZ2pXy4C2kOAP\n"
            + "p1eKMXliFNXL2MmfAwqay7ZggIF2wmTmLqifBDpIw7yWqjUV1kBggmw3so59rqim\n"
            + "FbQCEf4rd8NGhQFSJ/AGMTv0fIgmN/7hlCwFq4LEJoheezPfVoHhdvNP09/yMNHS\n"
            + "a5BL4ApXEKD6S2ck0I/dJNwtWzvzyMpFhepRYCt5AScecVaFvHhV9d3dunr1v3Pu\n"
            + "Lxa/1VHjnsTn2ZZWUPai/2jNeG4RS35ChNkICi4+iuO/qYeDE6L8Ws9ViRnIR6OE\n"
            + "5fHvDMdLZs78Tmjx16v8pN1uS7Qyqm4XDDIhxQIDAQABAoIBAHb2tRo8DE6fYB01\n"
            + "/LFF9I7J2RBqvAE6zH5gNQzobfMZ2Up+1wU6bUyZ7UrNa33D7VrFAQ7nXKBfjFbv\n"
            + "QvIFjRv/zmqAiXZJ6kNd5Zc3xwYMostWuOEZwfutDx+MPG5wuu3fQrWgTp5pZgga\n"
            + "ucJnxFThMcYBku3cMToTE28GQUdb1tn/WJxfaJsJOwVQqIrRe20HcaSVErnNL8cx\n"
            + "8MO8P8haKgHonYQPOeOOE8VUU1ZrN4kZukoceRFAEbt+n82QbswrToKlMirQXH42\n"
            + "hc7K07YooRRgygm1oRf0IDXvZnQTzdoeEW7mhKH0eHfKvBll1WulSctFTojB9nkn\n"
            + "oIodAMECgYEAzrVhihmwYntICaLONrOPIPk21KY+BvjAAbHb6lS4okw872fPWVk/\n"
            + "QWqKI2aRd4Gv8PL2Hg1OHaHo5H+P2oDqulypQqP2qDZJ7esJOFCYRQzpQKwVJNkj\n"
            + "5UhkpoxzxPB7yKiQBKYcFLgZr7xrkzLmEnIDrvXmKK0LCyLMpQbMy+0CgYEAzLeC\n"
            + "uOmLYx9MBeaOs8TcSmiY95T5yz/5yEXhtcU2DOLKTPYFlnTHsq5wduhl5Q0FhKD6\n"
            + "Bbx3oAgBK96mIqd9Po1fOAIm5zhsPiHF90EoDWWby2ufVq8ABpUJVr1DQfwuLb7y\n"
            + "nKcBEfuzyxR7NLgcHLXlUvH6/ErB87jshauoYjkCgYEAr6jp697qyrXzQOUOkuPC\n"
            + "VQYTt2BSw7/uA0KvHRLEGH/rJJO2XDqAi8nK/Ex0f/dAmeUOi/lIkdv+uvdAOzJk\n"
            + "xtxppk0KFmaUKtAadLXgwIU5TxEtorO4UmdTyUkANLMcrie4w4qZwG22vmXDkUKm\n"
            + "y7QCN1OWNKjhAqSe/fqqXP0CgYEAn1rOKDCr7wnivMkfFjLMpCNNx5Ow6pUvU8WK\n"
            + "EFumWluDzGrKKLdY5+8/3l/DSYxcWkSQuzxdBjaU6thTDLDCHpofn9KVA/H42Fij\n"
            + "6HdQvTT2FF//VZO9+mGzNlD3dPrf235DDsw1WhFLgG6+BWi3gWXaUDnK/O8YkSbG\n"
            + "/rqDxYkCgYBxKXnXv1/nGmk9SurdnSWBZVlWRVugrDeTcN21G2bMebClSub9ga8s\n"
            + "uWfL+LwwfhDFGSLMnjsK9kRIlaZv9WXRPGL5AU3frSW/CMkjZQYGtvkWsSDHCubS\n"
            + "fwTM8kDVfhGl4oA+0oX1c5loGyUoiWu0Xt1VbIYWlYrPgoUJYjehaQ==\n"
            + "-----END RSA PRIVATE KEY-----";

    public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n"
            + "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApUy28DCqyiq9wOCKd0U7\n"
            + "6hBNx40s18vh30OoZ2pXy4C2kOAPp1eKMXliFNXL2MmfAwqay7ZggIF2wmTmLqif\n"
            + "BDpIw7yWqjUV1kBggmw3so59rqimFbQCEf4rd8NGhQFSJ/AGMTv0fIgmN/7hlCwF\n"
            + "q4LEJoheezPfVoHhdvNP09/yMNHSa5BL4ApXEKD6S2ck0I/dJNwtWzvzyMpFhepR\n"
            + "YCt5AScecVaFvHhV9d3dunr1v3PuLxa/1VHjnsTn2ZZWUPai/2jNeG4RS35ChNkI\n"
            + "Ci4+iuO/qYeDE6L8Ws9ViRnIR6OE5fHvDMdLZs78Tmjx16v8pN1uS7Qyqm4XDDIh\n"
            + "xQIDAQAB\n"
            + "-----END PUBLIC KEY-----";

}
