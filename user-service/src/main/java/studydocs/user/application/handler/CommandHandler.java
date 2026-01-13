package studydocs.user.application.handler;
public interface CommandHandler<C, R> {
    R handle(C command);
    Class<C> commandType();
}