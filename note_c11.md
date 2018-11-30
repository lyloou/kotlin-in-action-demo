The general idea of a domain-specific language has existed for almost as long as the idea
of a programming language as such. We make a distinction between a general-purpose
programming language, with a set of capabilities complete enough to solve essentially
any problem that can be solved with a computer, and a domain-specific language, which
focuses on a specific task, or domain, and forgoes the functionality which is irrelevant for
that domain.
p322

Note that a lambda with receiver looks exactly the same as a regular lambda in the
source code. To see whether a lambda has a receiver, you need to look at the function to
which the lambda is passed, and its signature will tell you whether the lambda has a
receiver, and if it does, what its type is. For example, you can look at the declaration of
buildString , see that it takes a lambda of type StringBuilder.() -> Unit , and
conclude from this that in the body of the lambda you can invoke StringBuilder
methods without a qualifier.
p330

