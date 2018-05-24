package exceptions;

import java.util.Objects;

import javafx.application.Preloader.PreloaderNotification;

public class ErrorMessage implements PreloaderNotification {
	
	public static final ErrorMessage SUCCESSFULLY_DONE = new ErrorMessage(true, false);
    public static final ErrorMessage FAILED = new ErrorMessage(false, true);
	
	private double progress;
    private String message;
    private boolean done;
    private boolean failed;
    
    public ErrorMessage(double progress, String message)
    {
        this.progress = progress;
        this.message = message;
        this.done = false;
        this.failed = false;
    }

    private ErrorMessage(boolean done, boolean failed)
    {
        this.done = done;
        this.failed = failed;
    }

    
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ErrorMessage message1 = (ErrorMessage) o;
        return Double.compare(message1.progress, progress) == 0 &&
               done == message1.done && failed == message1.failed &&
               Objects.equals(message, message1.message);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(progress, message, done, failed);
    }

    @Override
    public String toString()
    {
        return "ProgressMessage{" + "progress=" + progress + ", message='" +
               message + '\'' + ", done=" + done + ", failed=" + failed + '}';
    }

    public double getProgress(){return progress;}
    public String getMessage(){return message;}
    public boolean isDone(){return done;}
    public boolean isFailed(){return failed;}
}
