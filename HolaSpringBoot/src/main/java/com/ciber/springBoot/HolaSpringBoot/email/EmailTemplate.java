/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.email;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.ciber.springBoot.HolaSpringBoot.constants.EmailConstants;

/**
 * @author ciber
 *
 */
public class EmailTemplate {
	private String templateId;

	private String template;

	private Map<String, String> replacementParams;

	public EmailTemplate(String templateId) {
		this.templateId = templateId;
		try {
			this.template = loadTemplate(templateId);
		} catch (Exception e) {
			this.template = EmailConstants.BLANK;
		}
	}

	private String loadTemplate(String templateId) throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(templateId).getFile());
		String content = EmailConstants.BLANK;
		try {
			content = new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			throw new Exception("Could not read template with ID = " + templateId);
		}
		return content;
	}

	public String getTemplate(Map<String, String> replacements) {
		String cTemplate = this.getTemplate();

		if (StringUtils.isEmpty(cTemplate)) {
			for (Map.Entry<String, String> entry : replacements.entrySet()) {
				cTemplate = cTemplate.replace("{{" + entry.getKey() + "}}", entry.getValue());
			}
		}

		return cTemplate;
	}

	/**
	 * @return the templateId
	 */
	public String getTemplateId() {
		return templateId;
	}

	/**
	 * @param templateId the templateId to set
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * @return the replacementParams
	 */
	public Map<String, String> getReplacementParams() {
		return replacementParams;
	}

	/**
	 * @param replacementParams the replacementParams to set
	 */
	public void setReplacementParams(Map<String, String> replacementParams) {
		this.replacementParams = replacementParams;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmailTemplate [templateId=" + templateId + ", template=" + template + ", replacementParams="
				+ replacementParams + "]";
	}


	
	
	
	
}
